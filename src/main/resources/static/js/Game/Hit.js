$(document).ready(function() {
    const boardSize = 20;
    const $gameBoard = $("#gameBoard");

    let snake, apple, score, direction, speed, level, obstacles, timeSurvived, timeLeft, game, levelTimer;
    let gameStarted = false;

    const $timer = $("#timer");
    const $score = $("#score");
    const $titleScreen = $("#titleScreen");
    const $gameContainer = $("#gameContainer");
    const $gameOverScreen = $("#gameOverScreen");
    const $startButton = $("#startButton");
    const $restartButton = $("#restartButton");
    const $titleButton = $("#titleButton");

    // 게임 초기화 함수
    function initializeGame() {
        snake = [{ x: 9, y: 10 }];
        apple = { x: Math.floor(Math.random() * boardSize), y: Math.floor(Math.random() * boardSize) };
        score = 0;
        direction = undefined;
        speed = 200;
        level = 1;
        obstacles = [];
        timeSurvived = 0;
        timeLeft = 60;
        $score.text(`점수: ${score}`);
        $timer.text(timeLeft);
        gameStarted = false;

        initializeBoard(); // 게임 보드 초기화 호출
    }

    // 게임 보드 초기화 함수
    function initializeBoard() {
        $gameBoard.empty(); // 기존 게임 보드 비우기
        for (let i = 0; i < boardSize; i++) {
            let $row = $("<tr></tr>");
            for (let j = 0; j < boardSize; j++) {
                let $cell = $("<td></td>");
                $row.append($cell);
            }
            $gameBoard.append($row);
        }
    }

    // 게임 시작 함수
    function startGame() {
        initializeGame();
        $titleScreen.hide();
        $gameContainer.show();
        $gameOverScreen.hide();

        // 게임 시작 시 방향키 이벤트 리스너 추가
        $(document).on("keydown", directionControl);
        game = setInterval(draw, speed);
        levelTimer = setInterval(updateLevel, 1000);
        gameStarted = true; // 게임 시작 플래그 true로 설정
    }

    // 게임 종료 함수
    function gameOver() {
        clearInterval(game);
        clearInterval(levelTimer);
        $gameOverScreen.show();
        gameStarted = false; // 게임 시작 플래그 false로 설정
    }

    // 방향 제어 함수
    function directionControl(event) {
        if (!gameStarted) return; // 게임이 시작되지 않았으면 함수 종료

        if (event.which === 37 && direction !== "RIGHT") direction = "LEFT";
        else if (event.which === 38 && direction !== "DOWN") direction = "UP";
        else if (event.which === 39 && direction !== "LEFT") direction = "RIGHT";
        else if (event.which === 40 && direction !== "UP") direction = "DOWN";
    }

    // 충돌 체크 함수
    function collision(newHead, array) {
        for (let i = 0; i < array.length; i++) {
            if (newHead.x === array[i].x && newHead.y === array[i].y) return true;
        }
        return false;
    }

    // 레벨 업데이트 함수
    function updateLevel() {
        if (!gameStarted) return;

        timeSurvived++;
        timeLeft--;
        $timer.text(timeLeft);

        if (timeLeft <= 0) {
            timeLeft = 60;
            level++;
            alert(`Next level: ${level}`);
            if (level === 2) {
                speed /= 2;
                clearInterval(game);
                game = setInterval(draw, speed);
            } else if (level === 3) {
                generateObstacles();
            }
        }
    }

    // 그리기 함수
    function draw() {
        if (!gameStarted) return; // 게임이 실행되지 않으면 동작하지 않겠다는 코드.

        $gameBoard.find("td").removeClass("snake apple obstacle");
        /* ↑ id gameBoard인 table 태그 안에 td에서 snake, apple, obstacle 이 세 개를 찾아서 지워서
            화면을 초기화 해주는 코드 (직전 게임 상태가 새롭게할 게임에 영향을 주지 않기 위해 초기화를 해준다.)
        */
        for (let i = 0; i < snake.length; i++) {
            let $cell = $gameBoard.find("tr").eq(snake[i].y).find("td").eq(snake[i].x);
            $cell.addClass("snake");
        }
        /*
        지렁이의 몸을 그리기 위해 반복문을 통하여 $gameBoard에서 tr에서 snake[i].y를 찾아 선택하고
        td에서 snake[i].x를 찾아 선택한 후에 찾은 값에 대해 $cell에 snake를 추가해준다.
        */

        $gameBoard.find("tr").eq(apple.y).find("td").eq(apple.x).addClass("apple");
        /*
        ↑ 지렁이를 그린 동일한 방식으로 사과를 그려주는 코드. 반복문을 사용하지 않은것은 사과는 항상 단일 셀에 존재하기 때문에
        늘어나거나 줄어들지 않아서 클래스 추가만으로 충분하다.
        */

        for (let i = 0; i < obstacles.length; i++) {
            $gameBoard.find("tr").eq(obstacles[i].y).find("td").eq(obstacles[i].x).addClass("obstacle");
        }
        /*
        ↑ 장애물 그리는 코드이고, $gameBoard의 tr에서 obstacles[i].y를 찾고, td에서 obstacles[i].x를 찾아
        클래스 obstacle를 추가해줌으로서 화면에 출력된다. 장애물은 화면 곳곳에 복수로 나타나기 때문에 반복문을 사용한다.
        */


        let snakeX = snake[0].x;
        // ↑ 지렁이의 머리 부분에 해당하는 x값 snake[0]은 snake배열의 첫 번째 해당하는 index.
        let snakeY = snake[0].y;
        // ↑ 지렁이의 머리 부분에 해당하는 y값 snake[0]은 snake배열의 첫 번째 해당하는 index.

        if (direction === "LEFT") snakeX--;
        // 현재 방향이 "LEFT"라면 snakeX 값을 1 감소, 머리가 왼쪽으로 한 칸 이동.
        if (direction === "UP") snakeY--;
        // 현재 방향이 "UP"라면 snakeY 값을 1 감소, 머리가 위쪽으로 한 칸 이동.
        if (direction === "RIGHT") snakeX++;
        // 현재 방향이 "RIGHT"라면 snakeX 값을 1 증가, 머리가 오른쪽으로 한 칸 이동.
        if (direction === "DOWN") snakeY++;
        // 현재 방향이 "DOWN"라면 snakeY 값을 1 증가.

        if (snakeX < 0 || snakeY < 0 || snakeX >= boardSize || snakeY >= boardSize) {
            gameOver();
            return;
        }
        /* ↑ 지렁이가 보드의 경계에 닿았는지 유무를 판단하는 논리연산 가정문이다.
            snakeX < 0은 보드의 왼쪽 경계를 벗어났는지 확인한다.
            snakeY < 0은 보드의 위쪽 경계를 벗어났는지 확인한다.
            snakeX >= boardSize는 보드의 오른쪽 경계를 벗어났는지 확인한다.
            snakeY >= boardSize는 보드의 아래쪽 경계를 벗어났는지 확인한다.
            논리연산자 or에 해당하는 ||를 사용한건 조건들 중 하나라도 ture가 되면
            함수 gameOver()를 호출해서 게임 오버 하기 위해서이다.
        */

        if (snakeX === apple.x && snakeY === apple.y) { // 지렁이가 사과를 먹었는지 확인하는 가정문
            score++; // 사과를 먹었다면 점수를 더해준다.
            $score.text(`점수: ${score}`); // 점수를 화면에 출력해준다.
            do {
                apple = { x: Math.floor(Math.random() * boardSize), y: Math.floor(Math.random() * boardSize) };
            } while (collision(apple, snake) || collision(apple, obstacles));
            /*
            ↑ do...while은 조건이 참일 때까지 특정 코드 블록을 실행하는 반복문이고
            사용한 이유는 게임이 진행되는 동안 몇 번이고 상관없이 랜덤하게 사과를 배치하며
            충돌방지(현재 뱀 위치와 새로 생길 사과 위치가 동일해지는 것을 방지하는 것.)
            작업도 동시에 진행 하기 위해서 do...while을 사용.
             x 좌표와 y 좌표에 랜덤한 위치에 사과를 생성하는데, while 안에 조건이 참이 될때까지
             do를 다시 실행합니다. 즉 충돌방지 조건을 만족할때까지 사과를 랜덤하게 계속 생성하고
             충돌이 발생하지 않으면서 사과를 생성했다면 조건문은 동작을 멈춘다.
            */

        } else {
            snake.pop();
            /* 사과를 먹지 않았다면 지렁이의 길이를 유지하기 위해 꼬리를 하나 제거 한다.
            이 작업을 통해서 지렁이가 이동하는 것처럼 보이게 된다.
            */

        }

        let newHead = { x: snakeX, y: snakeY };
        /* snakeX와 snakeY는 현재 지렁이 머리 위치를 기준으로 이동한 새로운 위치
        이 위치를 { x: snakeX, y: snakeY } 객체로 생성하여 newHead 변수에 저장
        이 객체는 지렁이의 움직일때마다 기준이 되는 새로운 머리 위치
        */

        if (collision(newHead, snake) || collision(newHead, obstacles)) {
            gameOver();
            return;
        }
        /* 지렁이의 새로운 머리와 몸이 충돌하는지 혹은 장애물과 충돌하는지
        둘 중 하나라도 발생하면 함수 gameOver()를 호출해서 게임 오버가 된다.

        */
        snake.unshift(newHead);
        /*
        unshift는 JavaScript 배열 메소드 중 하나로,
        배열의 시작 부분에 하나 이상의 요소를 추가하고,새로운 배열의 길이를 반환
        이 코드로 지렁이가 움직일때마다 newHead를 배열의 맨 앞에 추가해주고
        snake.pop()으로 꼬리를 제거해주니 지렁이가 움직이고 이동하는 것처럼
        보이는 효과를 준다.
        */
    }

    // 장애물 생성 함수
    function generateObstacles() {
        obstacles = [];
        for (let i = 0; i < 5; i++) {
            let obstacle = {
                x: Math.floor(Math.random() * boardSize),
                y: Math.floor(Math.random() * boardSize)
            };
            if (!collision(obstacle, snake) && !collision(obstacle, [apple])) {
                obstacles.push(obstacle);
            }
        }
    }

    // 게임 시작 버튼 클릭 이벤트 핸들러
    $startButton.click(function() {
        startGame();
    });

    // 게임 재시작 버튼 클릭 이벤트 핸들러
    $restartButton.click(function() {
        startGame();
    });

    // 타이틀로 돌아가기 버튼 클릭 이벤트 핸들러
    $titleButton.click(function() {
        $gameOverScreen.hide();
        $gameContainer.hide();
        $titleScreen.show();
    });
});