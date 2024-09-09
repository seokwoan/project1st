$(function(){

  var answer = new Array();
  var chance = 0;

  $("input").on( 'keyup' , function(){
    this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
  });

  $("#startButton").on( 'click' , function(){

    // 4자리 정답 생성

    while( answer.length != 4 ){
      var num = Math.floor( Math.random() * 9 + 1 );
      if( answer.indexOf( num ) == -1 ){
        answer.push( num );
      }
    }

    // 정답확인 버튼에 이벤트 추가
    $("#answerButton").on( 'click' , function(){]
    // 정답확인 버튼 누르면
    // 입력한 숫자 중복 확인 -> 정답과 입력숫자 비교 -> css 변경 -> input name 변경 -> input 태그 추가 -> chance 변수 증가 -> chance 변수 6되면 게임 종료
    //                      정답 맞추면 chance 별로 점수 추가 -> 게임 종료

      // 입력한 숫자 중복 확인
      for( var i = 0 ; i < 3 ; i++ ){
        for( var j = 1 ; j < 4 ; j++ ){
          if( i < j ){
            if( parseInt( $("input[name=num]").eq(i).val() ) == parseInt( $("input[name=num]").eq(j).val() ) ){
              alert( "숫자는 중복되지 않습니다" );
            }
          }
        }
      }

      // 정답과 입력한 숫자 비교
      for( var i =0 ; i < 4 ; i++ ){
        var temp = parseInt( $("input[name=num]").eq(i).val() );
        console.log(temp);
        var change = $("input[name=num]").eq(i);
        change.removeClass("num");
        change.addClass("noNum");

        for ( var j = 0 ; j < 4 ; j++ ){

          if( answer.indexOf( temp ) != -1 ){
            change.removeClass("noNum");
            change.addClass("nearNum");
          }

          if( i == j ){
            if( temp == answer[j] ){
              change.removeClass("nearNum");
              change.addClass("correctNum");
            }
          }
        }
      }

    });

    console.log( answer );

    // 게임시작시 게임시작 버튼 비활성화
    $("#startButton").off( 'click' );

  });

});


/*
게임시작 -> 랜덤한 4개의 숫자 배열에 저장( 중복 없이 )-정답
	-> 횟수 카운트 초기화
	-> 게임시작버튼 비활성화
	->

숫자 입력 -> 정답확인(button) 누르면
	- > input태그(.num)에서 숫자 가져옴 -> 입력된 숫자는 전부 달라야함
	-> 입력된 숫자중 같은 숫자가 있으면 다시 입력
		-> 입력된 숫자가 전부 다르면 정답과 비교
		-> 비교해서 위치와 숫자가 같으면 파란색으로 배경 변경
		-> 위치가 다르지만 숫자가 같으면 초록색으로 배경 변경
		-> 위치와 숫자가 전부 다르면 빨간색으로 배경 변경
		-> 횟수 카운트 +1
		-> #inputField 추가

	-> 입력한 숫자가 정답과 같으면 게임 종료 점수 추가
		-> 게임 종료되면 게임시작 버튼 활성화
		-> 게임결과 데이터베이스 저장

	-> 횟수 카운트가 6이되면 게임 종료 점수 없음
		-> 게임결과 데이터베이스 저장
		-> 게임 종료되면 게임시작 버튼 활성화


*/