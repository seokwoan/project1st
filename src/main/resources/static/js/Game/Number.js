$(function(){

  var answer = new Array(); // 정답이 담길 배열
  var chance = 0; // 정답 확인 횟수
  var totalChance = 6; // 정답확인 기회
  var score = 0; // 점수
  var answerCount = 0; // 자리에 맞는 숫자의 수

  // 숫자 제외 문자 입력 막기
  $("input").on( 'keyup' , function(){
    this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');
  });

  // 게임시작 버튼 클릭하면 게임시작 함수 호출
  $("#startButton").on( 'click' , function(){
    gameStart( answer , chance , totalChance , answerCount , score );
  });

});

// 게임시작 함수
function gameStart( answer , chance , totalChance , answerCount , score ){

  // 점수 초기화
  $("#score").text("");

  // 시작버튼 비활성화
  $("#startButton").off( 'click' );

  // 이전 사용한 결과값 지우기
  $(".result").empty();
  for( var i = 0 ; i < 4 ; i++ ){
    $("input[name=num]").eq(i).val("");
  }
  $("input[name=score]").eq(i).val("");


  // answer , chance , score 초기화
  answer = [];
  chance = 0;
  score = 0;

  // 4자리 정답 생성
  while( answer.length != 4 ){
    var num = Math.floor( Math.random() * 9 + 1 );
    if( answer.indexOf( num ) == -1 ){
      answer.push( num );
    }
  }
  console.log( answer );

  // 정답확인 버튼에 이벤트 추가
  $("#answerButton").on( 'click' , function(){
  // 정답확인 버튼 누르면
  // 입력한 숫자 중복 확인 -> 정답과 입력숫자 비교 -> div 추가 -> input value 가지고 비교한 결과로 div 배경 변경 -> chance 변수 증가 -> chance 변수 6되면 게임 종료
  //                      정답 맞추면 chance 별로 점수 추가 -> 게임 종료

    // 입력한 숫자 중복 확인
    for( var i = 0 ; i < 3 ; i++ ){
      for( var j = 1 ; j < 4 ; j++ ){
        if( i < j ){
          if( parseInt( $("input[name=num]").eq(i).val() ) == parseInt( $("input[name=num]").eq(j).val() ) ){
            alert( "숫자는 중복되지 않습니다" );
            return;
          }
        }
      }
    }

    // resultField div 추가
    $(".result").append( '<div class="resultField"></div>' );
    // resultField div 하위에 결과를 표시할 div 생성
    for( var i = 0 ; i < 4 ; i++ ){
      $(".resultField").eq(chance).append( '<div class="resultBox"></div>' );
    }
    // css 맞추기 위한 빈 박스
    $(".resultField").eq(chance).append( '<div class="emptyBox"></div>' );


    // chance 증가
    chance++;

    // 초기화
    answerCount = 0;

    // 인풋태그에서 값 가져오기
    for( var i =0 ; i < 4 ; i++ ){
      var temp = parseInt( $("input[name=num]").eq(i).val() );
      console.log(temp);
      var change = $(".resultBox").eq( 4 * ( chance - 1 ) + i );

      // input 값 div 넣기
      change.text( temp );

      change.addClass("noNum");

      // 인풋값에 따른 css 추가 및 점수 추
      if( answer.indexOf( temp ) != -1 ){
        change.removeClass("noNum");
        change.addClass("nearNum");
        // 점수 추가
        if( i == answer.indexOf( temp ) ){
          score += 500;
        }
        else{
          score += 300;
        }

      }

      for( var j = 0 ; j < 4 ; j++ ){
        if( i == j ){
          if( temp == answer[j] ){
            change.removeClass("nearNum");
            change.addClass("correctNum");
            answerCount++;
          }
        }
      }

      // 4자리가 전부 맞으면 게임 종료 - 엔드함수 호출
      if( answerCount == 4 ){
        alert( "정답입니다!");
        score += ( totalChance - chance ) * 2000;
        $("#score").text(score);
        end( answer , chance , totalChance , answerCount , score );
      }
    }

    // chance 6이면 게임 종료 - 엔드함수 호출
    if( answerCount != 4 && chance == totalChance ){
      alert( "게임 종료! 모든 기회를 사용했어요" );
      end( answer , chance , totalChance , answerCount , score );
    }

    $("#score").text(score);

  });
}

function end( answer , chance , totalChance , answerCount , score ){
  $("input[name=score]").val(score);
  $("#answerButton").off( 'click' );
  $("#startButton").on( 'click' , function(){
     gameStart( answer , chance , totalChance , answerCount , score );
  });

  var header = $("meta[name=_csrf_header]").attr("content");
  var token = $("meta[name=_csrf]").attr("content");
  var gameScore = parseInt( $("#score").text() );
  var url = "/game/Number/" + gameScore ;

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