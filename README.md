// 게임 점수 저장
게임에서 end함수 호출 -> ajax를 통해 데이터를 보내줌( 주소의 parameter를 통해 )

 -> 데이터를 저장하기 위한 Dto와 데이터베이스로 전달하기 위한 Entity가 필요
Dto를 만들어주고 ModelMapper를 통해 Dto <-> Entity 변경메소드를 만듬

Entity는 MemberEntity의 id를 연결해 점수를 세운 플레이어를 기록
Auditing 기능을 가진 PlayDate를 상속해 점수를 달성한 시간 기록
	-> AuditingEntity가 작동하기 위해서는 AuditingAware를가 필요
	-> AuditConfig를 통해 AuditingAware 동작


gameControl에서 ajax에서 보내온 데이터를 받아줌( @PathVariable을 통해 파라미터값 받음)
-> gameService의 scoreSave실행 
-> joinColumn인 member_id, 점수 , 게임타입, 시간 저장



// 게임 점수 불러오기
MyPage( controller ) 에서 게임전적을 불러옴
-> myPageService에서 getHistory 메소드 실행
-> 로그인한 아이디를 통해 member_id를 가져옴
-> 게임의 repository에 jpa이름 규칙을 통해 로그인한 user의 게임전적을 시간순으로 불러옴( List<Entity>저장 )
-> List에 저장된 Entity를 Dto로 변경해 DtoList에 저장
-> 저장한 DtoList를 return
-> MyPage( controller )에서 반환된 DtoList를 model을 통해 thymeleaf에 전달
-> GameRecord를 표시할 html 파일에서 th:each를 통해 기록 보여줌


// 프로젝트 진행 순서

1. th:fragment를 통해 레이아웃 작성
2. css 작성
3. 데이터저장을 위한 Dto , 데이터베이스 저장, 접속을 위한 Entity, repository작성
4. 회원가입 및 로그인 기능 만들기
5. 게임 만들기( 1개 우선)
6. 만든 게임의 점수 데이터 베이스 저장을 위한 작업( mapping, ajax 등)
7. 데이터베이스에 점수 저장 성공하면 게임 3개 더 만들기
8. 4개의 게임 전부 문제 없으면 게임기록 불러오는 작업
