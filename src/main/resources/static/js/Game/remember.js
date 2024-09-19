$(function(){

  $("#startButton").on( 'click' , function(){
    gameStart( );
  });

});

function gameStart(){

  // 시작버튼 비활성화
  $("#startButton").off( 'click' );

  var answer = new Array(); // 박스 누를 순서를 정할 배열
  var score = 0;
  var clickCount = 0;

  // 시작시 점수 초기화
  $("#score").text( score );



  // 배열에 값 넣어주기
  while( answer.length != 9 ){
    var num = Math.floor( Math.random() * 9 ); // 9개의 박스 -> 0 ~ 8의 인덱스
    if( answer.indexOf( num ) == -1 ){
     answer.push( num );
    }
  }

  console.log( answer );

  // 맞춰야할 순서를 보여줌
  for( let i = 0 ; i < 9 ; i++ ){
    $(".selectBox").eq(answer[i]).removeClass( "background" ).addClass( "answerBox" ); // 박스의 이미지를 다른 이미지로 변경

    // 박스의 이미지를 순서대로 원래 이미지로 되돌려줌
    (function(i){
      setTimeout( function(){
        $(".selectBox").eq(answer[i]).removeClass( "answerBox" ).addClass( "background" );
      } , 1000 * i + 1000 );
    })(i)
  }

  // selectBox 클릭이벤트 추가 -> 순서를 다 보여주고 나서 클릭이벤트 추가
  setTimeout( function(){
    for( var i = 0 ; i < 9 ; i++ ){
      $(".selectBox").eq(i).on( 'click' , function(){
        $(this).removeClass( "background" );
        $(this).addClass( "answerBox" );
        var selectNum = $(this).data( "index" ); // 누른 박스의 값 가져오기
        console.log( selectNum);
        $(this).off( 'click' ); // 누른 박스 클릭이벤트 제거

        if( selectNum != answer[clickCount] ){
          alert( "실패" );
          end();
        }
        else{
          clickCount++;
          score += clickCount * 1000;
          $("#score").text( score );

          if( clickCount == 9 ){
            alert( "게임 클리어" );
            $("#score").text( score );
            end();
          }
        }
      });
    }
    alert( "시작" );
  } , 10000 );

}



function end(){
  // 시작버튼 활성화
  $("#startButton").on( 'click' , function(){
    gameStart();
  });
  $(".selectBox").off("click"); // 박스 클릭이벤트 제거

  // 박스 배경 변경
  $(".selectBox").removeClass( "answerBox");
  $(".selectBox").addClass( "background" );
}


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