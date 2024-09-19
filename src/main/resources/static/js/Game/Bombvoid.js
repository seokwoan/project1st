$(document).ready(function () {
    const boardSize = 20;
    const $gameBoard = $("#gameBoard");
    let gameStarted = false;

    let game = {
        board: Array(boardSize).fill().map(() => Array(boardSize).fill(null)),
        score: 0
    };

    const $titleScreen = $("#titleScreen");
    const $startButton = $("#startButton");
    const $gameContainer = $("#gameContainer");
    const $score = $("#score");
    const $gameOverScreen = $("#gameOverScreen");
    const $restartButton = $("#restartButton");
    const $titleButton = $("#titleButton");

    function Board_Reset_generate() {
        console.log('보드가 초기화됩니다.');
        $gameBoard.empty();
        for (let i = 0; i < boardSize; i++) {
            let $row = $("<tr></tr>");
            for (let j = 0; j < boardSize; j++) {
                let $cell = $("<td></td>");

                // 셀 클릭 이벤트
                $cell.click(function () {
                    if (userMove($(this), i, j)) {
                        plant_A_Bomb();  // 유저가 움직일 때마다 폭탄을 설치
                        if (check_Touch_Bomb($(this))) {  // 폭탄 클릭 확인
                            console.log('폭탄 클릭됨!');  // 폭탄 클릭 여부 로그
                            bombsChangeColor();
                            endGame('user'); // 게임 오버
                        }
                    }
                });
                $row.append($cell);
            }
            $gameBoard.append($row);
        }
    }

    $startButton.click(function () {
        console.log('게임이 시작됩니다.');
        $titleScreen.hide();
        $gameContainer.show();
        resetGame();
        gameStarted = true;
    });

    $restartButton.click(function () {
        resetGame();
        $gameOverScreen.hide();
        gameStarted = true;
    });

    $titleButton.click(function () {
        $gameOverScreen.hide();
        $gameContainer.hide();
        $titleScreen.show();
        gameStarted = false;
    });

    function userMove($cell, x, y) {
        // 유저가 움직였을 때 폭탄이 있거나 유저가 이미 클릭한 셀인 경우 무시
        if ($cell.hasClass('user')){
            console.log('이미 선택된 셀입니다.');
            return false;
        }

        $cell.addClass('user');
        game.board[x][y] = 'user';
        game.score += 1;
        $score.text(`점수: ${game.score}`);
        return true;
    }

    function plant_A_Bomb() {
        let emptyCells = [];
        $("#gameBoard td").each(function () {
            if (!$(this).hasClass('user') && !$(this).hasClass('Bomb')) {
                emptyCells.push($(this));
            }
        });

        if (emptyCells.length === 0) return;

        let randomCell = emptyCells[Math.floor(Math.random() * emptyCells.length)];
        let x = randomCell.parent().index();
        let y = randomCell.index();

        randomCell.addClass('Bomb');
        game.board[x][y] = 'Bomb';

        console.log('폭탄 설치 완료: ', x, y); // 폭탄 설치 로그
    }

    function bombsChangeColor() {
        $("#gameBoard td.Bomb").each(function (){
            $(this).css('background-color', 'darkred');
        })
    }

    // 폭탄이 있는지 확인하는 함수
    function check_Touch_Bomb($cell) {
        if ($cell.hasClass('Bomb')) {
            console.log('폭탄 감지됨!');  // 폭탄 감지 여부 로그
            return true;
        }
        return false;
    }

    function endGame(gameOver) {
        console.log('함정카드 발동');
        $gameOverScreen.show();
        gameStarted = false;

        if (gameOver === 'user') {
            $gameOverScreen.find('h1').text('폭탄이 터졌습니다. 당신은 사망하였습니다.');

        }
        var header = $("meta[name=_csrf_header]").attr("content");
        var token = $("meta[name=_csrf]").attr("content");
        var scoreText = $("#score").text();

        var gameScore = parseInt( scoreText.substring( scoreText.indexOf( " " ) ) );
        var url = "/game/Bombvoid/" + gameScore ;

        $.ajax({
          url : url,
          type : "POST",
          cache : false,
          beforeSend : function(xhr){
            xhr.setRequestHeader(header, token);
          },
          success : function(result , status){
            alert("저장 성공");
          },
          error : function(jqXHR, status , error){
            if(jqXHR.status == "200")
              alert("로그인후 이용해주세요");
            else
              alert(jqXHR.responseText);
          }
        });
    }

    function resetGame() {
        game = {
            board: Array(boardSize).fill().map(() => Array(boardSize).fill(null)),
            score: 0
        };

        Board_Reset_generate();
        plant_A_Bomb();  // 게임이 시작할 때 폭탄을 배치합니다.
        $score.text('점수: 0');
        $gameOverScreen.hide();
    }
});