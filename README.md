# 공지사항 관리 REST API
<br>

## 서비스 구조 및 개발 전략
![image](https://github.com/skysrd/notice-management/assets/37787079/f79fcf4e-2f84-4f58-923b-4ce87fb460d2)

서비스는 Controller - Service - Repository로 이어지는 계층 구조를 채택해 개발했습니다.
요구사항의 데이터가 대단히 복잡하거나, 외부 MSA를 자주 사용하는 방식이 필요하지 않았기 때문에 간결한 계층 구조의 형태로 개발을 진행했습니다.

사용자 관리 기능은 MemberController - MemberService - MemberRepository로 이어지는 단일한 계층 구조를 가지도록 개발했습니다.

공지사항 관리 기능은 현재는 공지사항의 작성, 수정 및 삭제 사용자에 대한 제한 조건이 없는 상황이나, 공지사항이라는 도메인이 갖는 특성 상 중간에 Service Implementation 계층을 추가로 도입하여 개발했습니다.
이를 통해 이후 AOP의 도입, 사용자 권한 검증 로직 및 UserNoticeServiceImpl 등 추가로 개발한다면 기존 코드의 변경 없이 손쉽게 권한 검증에 따른 기능 확장이 가능할 것으로 기대합니다.

또한 첨부파일은 Controller가 없이 Service - Repository로 이어지는 계층 구조로 개발하였으며, 이는 첨부파일만을 관리하는 기능이 요구사항에 존재하지 않는 데에서 기인했습니다. AttachmentService는 NoticeService에 의해 호출 혹은 요청되어 파일 업로드 및 파일 경로 저장 기능을 수행합니다.

Spring Security 기능 도입을 검토하였으나, 사용자 정보의 사용 빈도가 많을 것으로 예측되지 않고, 이를 활용하는 기능은 요구에 존재하지 않았으므로 UUID를 발급하여 입력받는 것으로 갈음해 개발했습니다.

일반적으로 Controller 레벨에서 통합 테스트를 작성하지만, 이 과제에서는 Controller는 비즈니스 로직을 수행하지 않는 관계로, Service 레벨에서 통합 테스트를 진행하도록 작성했습니다.

---

<br>

## 기능 설명

---

### 사용자 관리 기능
|Method|URI|기능|
|---|---|---|
|POST|/api/member/signup|회원가입|

사용자 관리 기능은 현재 목표로 하는 공지사항 관리 서비스에서 작성자를 구분하기 위한 용도로 개발을 진행했으며, 이에 따라 회원 가입 단일한 기능만을 갖도록 개발되었습니다.

이후 역할 구분 기능을 개발, AOP를 도입하면 일반 사용자와 관리자의 접근을 분리하고, 관리자만 새로운 계정을 생성할 수 있도록 하는 등의 파생 기능을 추가로 개발할 수 있도록 개발했습니다.

---
### 공지사항 관리 기능
|Method|URI|기능|
|-|-|-|
|POST|/api/notice|공지사항 등록|
|GET|/api/notice|공지사항 리스트 조회|
|GET|/api/notice/detail|공지사항 상세 조회|
|PATCH|/api/notice|공지사항 수정|
|DELETE|/api/notice|공지사항 삭제|

공지사항 관리 기능은 공지사항의 삽입, 조회, 수정 및 삭제 기능을 제공합니다.

공지사항 등록 시에는 제목, 내용, 공지 시작 및 종료 일시, 첨부파일과 함께 **사용자 아이디**를 입력하도록 했습니다. 이를 통해 사용자의 구분 및 공지사항 작성자를 입력, 기록하도록 합니다.

공지사항 리스트 조회 기능은 공지 시작 및 종료 일시 기준에 부합하는 공지사항만을 리스트 형태로 출력하는 기능입니다. 현재 시각을 기준으로 하여 공지 시작 및 종료 일시를 비교, 출력이 필요한 공지사항만을 조회할 수 있습니다.

공지사항 상세 조회 기능은 공지 시작 및 종료 일시와는 무관하게 공지사항 아이디의 입력을 통해 해당 공지사항을 확인할 수 있는 기능입니다. 지난 공지 혹은 미게시 공지를 확인하는 기능이 필요하다고 판단되어 자의적으로 상세 조회 기능을 추가로 개발했습니다.

두 공지사항 조회 기능은 제목, 내용, 등록일시, 조회수, 작성자를 응답합니다.

공지사항 수정 기능은 공지사항 아이디를 통해 수정할 공지사항을 특정하고, 공지사항의 제목, 내용, 공지 시작 및 종료 일시를 수정할 수 있도록 했습니다.

공지사항 삭제 기능은 공지사항 아이디를 통해 삭제할 공지사항을 검색, 삭제하도록 했습니다.

---
<br>


## DB 구성 및 환경

![notice (4)](https://github.com/skysrd/notice-management/assets/37787079/e9a5d648-601c-4873-ba80-43b80ed26807)

Database는 H2 MemoryDB와 MySQL 두 가지를 병용하여 개발했습니다.
고정된 DB가 없는 상황에서 사용할 수 있도록 H2 Memory DB를 통해 기능을 테스트할 수 있도록 했고,
MySQL과 같은 DB를 구축한 상황을 상정해 직접 DB의 값을 확인할 수 있도록 했습니다.

아래의 구동 방법에서는 Memory DB를 사용하여 구동할 수 있도록 했습니다.

---

#### 사용자 (Members)
| 컬럼 명 | 컬럼 타입 | 컬럼 설명 |
|---|---|---|
|member_id|BINARY(16)|사용자 고유 아이디|
|member_role|VARCHAR(10)|사용자 역할|

사용자 역할은 공지사항의 특성 상 서비스 혹은 프로젝트의 관리자만 접근이 가능하도록 추가로 개발할 소지가 있다고 판단되어 추가로 member_role이라는 Column을 가지도록 구성했습니다.

---
#### 공지사항 (Notices)
| 컬럼 명 | 컬럼 타입 | 컬럼 설명 |
|---|---|---|
|notice_id|BINARY(16)|공지사항 고유 아이디|
|title|VARCHAR(255)|공지사항 제목|
|content|TEXT|공지사항 본문|
|start_time|DATETIME|공지 시작 일시|
|end_time|DATETIME|공지 종료 일시|
|read_count|INT|공지사항 조회수|
|created_by|BINARY(16)|공지사항 작성자|
|created_time|DATETIME|공지사항 작성 일시|

---
#### 첨부파일 (Attachments)
| 컬럼 명 | 컬럼 타입 | 컬럼 설명 |
|---|---|---|
|attachment_id|BINARY(16)|첨부파일 고유 아이디|
|caller_id|BINARY(16)|호출자 아이디|
|attachment_path|TEXT|파일 경로|

첨부파일은 공지사항 외에도 첨부파일을 사용할 수 있는 다양한 호출 주체가 생길 수 있는 점을 감안하여 caller_id를 FK로 사용하지 않아 연결관계가 생기지 않도록 구성했습니다.
이를 통해 추후 기타 게시글 등에서도 동일한 형태의 첨부파일 사용이 가능합니다.
<br>
파일 경로는 S3 Bucket에 업로드한 파일 객체에 접근할 수 있는 경로를 의미합니다.

---
<br>

## 구동 방법
``` ./gradlew bootJar -Dspring.profile.active=memh2```

---
<br>

## 회고
- 공지사항 조회 및 수정 시의 첨부파일 처리에 관한 부분이 명시되어 있지 않아 엄밀하게 개발하지 못한 점이 아쉽다.
- 공지사항 외에도 다양한 방법으로 첨부파일을 사용할 수 있도록 개발했으나, 적용하지 못한 점이 아쉽다.
- 컨트롤러에서는 비즈니스 로직을 수행하지 않기 때문에 서비스 레이어에서 통합 테스트를 진행하도록 개발했으나, 서비스 레이어의 createNotice와 같은 메소드는 단위 테스트를 진행하기 적합하지 않은 형태로 개발되어 충실하게 단위 테스트를 작성하지 못한 부분이 아쉽다.
