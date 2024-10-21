import streamlit as st
import requests

# API URL (Spring Boot 서버의 엔드포인트)
API_URL = "http://localhost:8080/api/v1/member/register"

def signup_page(switch_page):
    st.title("회원가입 페이지")

    # 사용자 입력 받기
    email = st.text_input("이메일")
    password = st.text_input("비밀번호", type="password")
    name = st.text_input("이름")
    birthdate = st.date_input("생년월일")
    gender = st.radio("성별", ("M", "F"))

    # 회원가입 버튼
    if st.button("회원가입"):
        # 사용자의 입력값을 JSON 형태로 준비
        member_data = {
            "email": email,
            "password": password,
            "name": name,
            "birthdate": birthdate.strftime("%Y-%m-%d"),  # 날짜를 문자열로 변환
            "gender": gender
        }

        # Spring Boot API로 POST 요청 보내기
        response = requests.post(API_URL, json=member_data)

        # 응답 처리
        if response.status_code == 200:
            st.success("회원가입 성공!")
            st.write(response.json())  # 응답 내용을 출력 (회원 정보 등)
            switch_page("main")
        else:
            st.error(f"회원가입 실패: {response.status_code}")
            st.write(response.text)  # 에러 메시지 출력
