import streamlit as st

def login_page(switch_page):
    st.title("로그인 페이지")

    # 로그인 폼
    with st.form("login_form"):
        email = st.text_input("이메일")
        password = st.text_input("비밀번호", type="password")
        submitted = st.form_submit_button("로그인")

        if submitted:
            st.write(f"로그인 시도: {email}")

    if st.button("메인 페이지로 돌아가기"):
        switch_page("main")
