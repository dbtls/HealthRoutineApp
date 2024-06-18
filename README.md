# AI 운동루틴 추천 앱 Seven T
<p align="center">
  <img src="https://github.com/dbtls/HealthRoutineApp/assets/29426412/ab48e27b-505b-4444-90cb-b389325da192">
</p>


## 소개
- 개인의 체성분, 환경, 신체 상황 등을 기반으로 맞춤형 운동 루틴을 추천해 주는 애플리케이션
- 텍스트 입력 형식의 LLM을 GUI의 형태로 바꿔 보다 직관적이고 쉽게 운동 설정 가능

## 팀원
- 김나현
- 김성환
- 김유신
- 김종수
- 오승민
- 조단
- 한수정
- 
## 사용 기술
- Andriod Studio
- Spring Boot
- Spring JPA
- Mysql
<br>
### 내 역할
- Spring 백엔드 API 구축
- DB 설계 및 개발
- Spring-DB 연동(JPA)
- Spring-Android Studio 연동(Retrofit)
- Clova Studio API 연동

## LLM

### Naver Clova Studio
- 대형 언어 모델 LLM을 활용하여 맞춤형 운동 루틴을 제공하기 위해 NAVER의 CLOVA Studio를 선택
- CLOVA Studio의 HyperCLOVA 언어 모델을 사용하여, 사용자의 신체 정보와 환경 데이터를 기반으로 적합한 운동을 추천하도록 프롬프트 튜닝을 진행
- 튜닝 과정에서 인바디 유형, 선호 부위, 1RM 무게, 운동 목표, 운동 환경 등의 입력값을 설정하고, 운동 이름, 중량, 횟수, 세트 등의 출력값을 정의함.
- 텍스트와 컴플리션 필드로 구성된 100여개의 데이터셋을 제작해 튜닝을 진행

### 데이터셋
<p align="center">
  <img src="https://github.com/dbtls/HealthRoutineApp/assets/29426412/e30292d6-c24e-451b-a0a7-8786638b08c4">
</p>

## 주요 기능

### 회원가입 / 나만의 루틴 생성

<table border="1" width="100%">
  <tr>
    <th width="50%">회원가입/로그인</th>
    <th width="50%">나만의 루틴 생성</th>
  </tr>
  <tr>
    <td align="center" valign="middle"><img src="https://github.com/dbtls/HealthRoutineApp/assets/29426412/a21d05c2-2602-4827-ba8b-acc607a75251" alt="회원가입" width="200"></td>
    <td align="center" valign="middle"><img src="https://github.com/dbtls/HealthRoutineApp/assets/29426412/d6719ad3-93de-41dc-af18-e50e21c156ff" alt="나만의 루틴생성" width="200"></td>
  </tr>
  <tr>
    <td align="center" valign="middle">아이디와 비밀번호를 기반으로하는 간단한 회원가입 기능. 기본적인 신체정보를 받아서 보다 정확한 추천 루틴을 제공.</td>
    <td align="center" valign="middle">사용자가 원하는 운동루틴을 직접 설정하는 기능.</td>
  </tr>
</table>

### 루틴 정보 / AI 루틴 추천

<table border="1" width="100%">
  <tr>
    <th width="50%">루틴 정보</th>
    <th width="50%">AI 루틴 추천</th>
  </tr>
  <tr>
    <td align="center" valign="middle"><img src="https://github.com/dbtls/HealthRoutineApp/assets/29426412/b29fe4cd-d24e-4f56-ae9b-18f317bc2dbd" alt="루틴 정보" width="200"></td>
    <td align="center" valign="middle"><img src="https://github.com/dbtls/HealthRoutineApp/assets/29426412/8c48110b-d42d-4a49-bd44-f52e27f798d0" alt="AI 루틴 추천" width="200"></td>
  </tr>
  <tr>
    <td align="center" valign="middle">만들어진 루틴 정보 출력, 운동을 클릭하면 운동 방법과 예시 영상을 제공.</td>
    <td align="center" valign="middle">운동을 원하는 부위, 운동 목적, 운동 장소를 선택하면 기존의 사용자 정보를 바탕으로 목적에 부합하는 운동 루틴을 추천.</td>
  </tr>
</table>

### 신체정보 변경 / 루틴 검색

<table border="1" width="100%">
  <tr>
    <th width="50%">신체정보 변경</th>
    <th width="50%">루틴 검색</th>
  </tr>
  <tr>
    <td align="center" valign="middle"><img src="https://github.com/dbtls/HealthRoutineApp/assets/29426412/7980181e-d87d-467f-b5e6-a79d3a6eede4" alt="신체정보 변경" width="200"></td>
    <td align="center" valign="middle"><img src="https://github.com/dbtls/HealthRoutineApp/assets/29426412/1f21fd2b-d5bb-40d0-ad3d-6796960b780d" alt="루틴 검색" width="200"></td>
  </tr>
  <tr>
    <td align="center" valign="middle">기존에 입력한 신체정보에 변화가 생겼다면, 신체정보를 세세하게 변경 가능.</td>
    <td align="center" valign="middle">루틴 목록 출력 및 특정 루틴 검색 기능.</td>
  </tr>
</table>
