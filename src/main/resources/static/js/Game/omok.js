$(document).ready(function (){
    const boardSize = 20;
    const $gameBoard = $("#gameBoard");
    let  gameStarted = false;

    let  game = {
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
        for (let i=0; i < boardSize; i++) {
            let $row = $("<tr></tr>");
            for (let j=0; j < boardSize; j++) {
                let $cell = $("<td></td>");

                $cell.click(function (){
                    if (userMove($(this), i, j)) {
                        if (checkWinner('user')) {
                            endGame('user'); // 유저 승리
                        } else {
                            comMove();
                            if (checkWinner('com')) {
                                endGame('com'); // 컴퓨터 승리
                            }
                        }
                    }
                })
                $row.append($cell);
            }
            $gameBoard.append($row);
        }
    }

    $startButton.click(function (){
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

    function userMove($cell,x,y) {
        if ($cell.hasClass('user') || $cell.hasClass('com'))
            return false;

        $cell.addClass('user');
        game.board[x][y] = 'user';
        game.score += 1;
        $score.text(`점수: ${game.score}`);
        return true;
    }

    function comMove() {
        let emptyCells = [];
        $("#gameBoard td").each(function () {
           if (!$(this).hasClass('user') && !$(this).hasClass('com')) {
               emptyCells.push($(this));
           }
        });

        if (emptyCells.length === 0) return;

        let randomCell = emptyCells[Math.floor(Math.random() * emptyCells.length)];
        let x = randomCell.parent().index();
        let y = randomCell.index();

        randomCell.addClass('com');
        game.board[x][y] = 'com';
    }

    function checkWinner(player) {
        for (let i = 0; i < boardSize; i++) {
            for (let j = 0; j < boardSize; j++) {
                if (game.board[i][j] === player) {
                    if (checkDirection(i, j, player, 1,0) || // 가로
                        checkDirection(i, j, player, 0,1) || // 세로
                        checkDirection(i, j, player, 1,1) || // 대각선 \
                        checkDirection(i, j, player, 1, -1)) { // 대각선 /
                        return true;
                    }
                }
            }
        }
        return false;
    }

    function checkDirection(x,y,player, xx, yy) {
        let count = 1;
        let i=1;

        while (inBounds(x + xx * i,y + yy * i) && game.board[x + xx * i][y + yy * i] === player) {
            count++;
            i++;
        }

        i = 1;

        while (inBounds(x - xx * i,y - xx * i) && game.board[x - xx * i][y - yy * i] === player) {
            count++;
            i++;
        }
        return count >= 5;
    }


    function inBounds(x,y) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
    }

    function endGame(winner) {
        $gameOverScreen.show();
        gameStarted = false;
        if(winner === 'user') {
            $gameOverScreen.find('h1').text('인간승리!');
        } else {
            $gameOverScreen.find('h1').text('옴닉승리!');
        }
    }

    function resetGame() {
        game = {
            board: Array(boardSize).fill().map(()=> Array(boardSize).fill(null)),
            score: 0
        };

        Board_Reset_generate();
        $score.text('점수: 0');
        $gameOverScreen.hide();
    }

}); // END