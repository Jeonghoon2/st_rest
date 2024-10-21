import streamlit as st

def main_page(switch_page):
    st.title("메인 페이지")

    if st.button("로그인"):
        switch_page("login")

    if st.button("회원가입"):
        switch_page("signup")
