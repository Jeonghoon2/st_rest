import streamlit as st

def main_page(switch_page):
    st.title("메인 페이지")

    if st.button("사용자 조회"):
        switch_page("get_users")

    if st.button("사용자 추가"):
        switch_page("signup")
