$(function(){

  var answer = new Array();
  var chance = 0;

  $("#start").on( 'click' , function(){
    for( var i = 0 ; i < 4 ; i++ ){
      var j = 0;
      var same = false;
      var num = Math.floor( Math.random() * 9 + 1 );
      while( j < answer.length ){
        if( answer[j] == num ){
          same =true;
        }
        j++;
      }
      if( same == false ){
        answer[i] = num;
      }
    }

    console.log( answer );
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