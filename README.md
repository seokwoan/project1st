프론트엔드 - 
	홈페이지 켜서 게임사이트 만들었다 
	4개의 게임을 만들었고 게임플레이 하려면 로그인해야함
	각 게임은 어떤식으로 플레이한다 한번씩 플레이 보여주고
	게임 하나씩 html, css, js에서 설명할 내용 설명( css는 디자인, js는 어떤 함수?)
	마이페이지에서는 플레이기록 볼 수 있음
	보여주기 위해서 th:each사용

	백엔드 - 
	프론트엔드 설명 끝나고

	th:fragment, replace를 사용해 페이지 레이아웃을 생성해 사용
	
	데이터베이스 저장을 위해 Dto, Entity, Repository 생성
	Dto - 
	memberDto는 passwordEncoder를 통해 비밀번호 암호화
	게임Dto는 점수, 게임타입, 플레이 날짜를 변수로 갖고 ModelMapper로
	dto <-> entity 변경

	Entity - 
	JoinColumn을 통해 member_id를 공유함
	플레이 시간을 저장하기 위해 PlayDate를 상속
	playDate가 기능을 하기위해
	AuditConfig를 통해 옵저버패턴 사용 

	Repository - 
	이름 규칙을 통해 JoinColumn인 member_id를 검색하고 시간을 기준으로 
	내림차순 정렬( 마이페이지 )
	findByMemberEntity_IdOrderByDateDesc
	joinColumn 변수명인 memberEntity에서 id가 같은걸 가져오고
	Date를 기준으로 Desc 내림차순 정렬
	
	Enum -
	repository에서 게임으로 검색하려 했으나 각 게임의 데이터베이스를 따로 
	사용 따로 사용하지 않음
