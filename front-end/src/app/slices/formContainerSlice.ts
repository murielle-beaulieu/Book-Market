import { createSlice } from "@reduxjs/toolkit";

export enum FormContainerEnum {
    LOG_IN = "LOG_IN",
    SIGN_UP = "SIGN_UP",
    IDLE = "IDLE"
}

type FormContainerState = {
  value:FormContainerEnum;
}

const initialState: FormContainerState = {
 value: FormContainerEnum.IDLE
};

export const formContainerSlice = createSlice({
    name: 'formContainer',
    initialState,
        reducers: {
        showLogInForm: (state) => {
          state.value = FormContainerEnum.LOG_IN;
        },
        showSignUpForm: (state) => {
          state.value = FormContainerEnum.SIGN_UP;
        },
        showIdle: (state) => {
          state.value = FormContainerEnum.IDLE;
        }
    }, selectors: {
    selectFormContainer: formContainer => formContainer.value,
  }
});

export const { showLogInForm, showSignUpForm, showIdle } = formContainerSlice.actions;
export const { selectFormContainer } = formContainerSlice.selectors;
export default formContainerSlice.reducer;