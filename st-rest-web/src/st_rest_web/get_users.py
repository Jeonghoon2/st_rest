import streamlit as st
import requests
from datetime import datetime

# API URL (Spring Boot 서버의 사용자 조회, 업데이트, 삭제 엔드포인트)
API_URL_GET_USERS = "http://localhost:8080/api/v1/member/all"
API_URL_UPDATE_USER = "http://localhost:8080/api/v1/member/update"
API_URL_DELETE_USER = "http://localhost:8080/api/v1/member/delete"

def get_users_page(switch_page):
    st.title("사용자 조회 및 업데이트 페이지")

    # API 요청으로 사용자 목록 가져오기
    response = requests.get(API_URL_GET_USERS)

    if response.status_code == 200:
        users = response.json()

        # 사용자 목록 테이블과 업데이트 및 삭제 버튼 제공
        for user in users:
            with st.form(key=f"user_form_{user['id']}"):
                st.write(f"### 사용자 ID: {user['id']}")

                # 각 사용자 데이터 입력 폼
                email = st.text_input("이메일", value=user["email"])
                name = st.text_input("이름", value=user["name"])

                # birthdate를 문자열에서 datetime 객체로 변환
                birthdate_str = user["birthdate"]
                try:
                    birthdate = datetime.strptime(birthdate_str, "%Y-%m-%d").date()
                except ValueError:
                    st.error(f"생년월일 형식 오류: {birthdate_str}")
                    birthdate = None

                birthdate = st.date_input("생년월일", value=birthdate)
                gender = st.selectbox("성별", ["M", "F"], index=0 if user["gender"] == "M" else 1)

                # 업데이트 버튼
                submitted = st.form_submit_button("업데이트")

                # 폼이 제출되었을 때 실행
                if submitted:
                    # 수정된 데이터를 서버로 전송
                    updated_data = {
                        "id": user["id"],
                        "email": email,
                        "name": name,
                        "birthdate": birthdate.strftime("%Y-%m-%d"),
                        "gender": gender
                    }
                    update_response = requests.put(API_URL_UPDATE_USER, json=updated_data)

                    if update_response.status_code == 200:
                        st.success(f"사용자 {user['id']} 정보가 성공적으로 업데이트되었습니다.")
                    else:
                        st.error(f"사용자 업데이트 실패: {update_response.status_code}")
                        st.write(update_response.text)

                # 삭제 버튼
                delete_button = st.form_submit_button("삭제")

                # 삭제 버튼이 클릭되었을 때 실행
                if delete_button:
                    delete_response = requests.delete(f"{API_URL_DELETE_USER}?id={user['id']}")
                    if delete_response.status_code == 200:
                        st.success(f"사용자 {user['id']}가 성공적으로 삭제되었습니다.")
                        st.rerun()
                    else:
                        st.error(f"사용자 삭제 실패: {delete_response.status_code}")
                        st.write(delete_response.text)

    else:
        st.error(f"사용자 조회 실패: {response.status_code}")
        st.write(response.text)  # 에러 메시지 출력

    # 메인 페이지로 돌아가는 버튼
    if st.button("메인 페이지로 돌아가기"):
        switch_page("main")
