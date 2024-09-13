$(function(){

  var answer = new Array(); // 박스 누를 순서를 정할 배열

  $("#startButton").on( 'click' , function(){
    gameStart( answer );
  });

});

function gameStart( answer ){

  var level = 1;
  var score = 0;

  // 게임 새로시작하면 answer 초기화
  answer.length = 0;

  // 배열에 값 넣어주기
  while( answer.length != $(".selectBox").length ){
    var num = Math.floor( Math.random() * 9 ); // 9개의 박스 -> 0 ~ 8의 인덱스
    if( answer.indexOf( num ) == -1 ){
     answer.push( num );
    }
  }

  // 레벨에 맞게 배열에서 값 빼오기


  for( var i = 0 ; i < $(".selectBox").length ; i++ )
  $(".selectBox").eq(i).on( 'click' , function(){
    select(); // selectBox 이벤트 추가 ,select 함수 실행
  })



  console.log( answer )

}


function select(){}

//  1단계 시작시
//		배열에서 하나의 값을 가져옴
//		가져온 하나의 값에 해당하는 seletBox가 빛난다?
//
//		누른 박스의 인덱스값을 가져옴
//		배열에서 가져온 하나의 값과 누른 박스의 인덱스가 같으면 성공
//		아니면 실패
//
//	성공하면 2단계 시작
//		2단계는 2개의 값을 가져옴
//		순서대로 빛나고
//
//		순서대로 누르고
//
//		성공하면 다음단계 계속( 5까지?)
//
//	실페하면
//		게임 종료
//		select박스 이벤트 제거
//		스타트 버튼 활성화
//
//	단계를 표시할 변수
//	점수 변수