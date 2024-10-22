import streamlit as st
from login import login_page
from signup import signup_page
from main_page import main_page
from get_users import get_users_page


def switch_page(page_name):
    st.write(f'<meta http-equiv="refresh" content="0; url=/?page={page_name}">', unsafe_allow_html=True)


query_params = st.query_params
page = query_params.get("page", "main")

if page == "main":
    main_page(switch_page)
elif page == "get_users":
    get_users_page(switch_page)
elif page == "signup":
    signup_page(switch_page)
