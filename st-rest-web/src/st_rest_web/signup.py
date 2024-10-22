import streamlit as st
import requests

API_URL = "http://localhost:8080/api/v1/member/register"


def signup_page(switch_page):
    st.title("사용자 추가 페이지")

    # 사용자 입력 받기
    email = st.text_input("이메일")
    password = st.text_input("비밀번호", type="password")
    name = st.text_input("이름")
    birthdate = st.date_input("생년월일")
    gender = st.radio("성별", ("M", "F"))

    if st.button("확인"):
        member_data = {
            "email": email,
            "password": password,
            "name": name,
            "birthdate": birthdate.strftime("%Y-%m-%d"),
            "gender": gender
        }

        # Spring Boot API로 POST 요청 보내기
        response = requests.post(API_URL, json=member_data)

        # 응답 처리
        if response.status_code == 200:
            st.success("사용자 추가 성공!")
            # st.write(response.json())
            switch_page("main")
        else:
            st.error(f"사용자 추가 실패: {response.status_code}")
            st.write(response.text)

    if st.button("취소"):
        switch_page("main")
