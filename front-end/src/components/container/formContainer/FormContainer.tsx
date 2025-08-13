import { useAppDispatch, useAppSelector } from "../../../app/hooks"
import {
  FormContainerEnum,
  selectFormContainer,
  showIdle,
  showLogInForm,
  showSignUpForm,
} from "../../../app/slices/formContainerSlice"
import LoginForm from "../../form/login-form/LogInForm"
import RegisterForm from "../../form/register-form/RegisterForm"
import styles from "./formContainer.module.scss"

export const FormContainer = () => {
  const dispatch = useAppDispatch()
  const currentFormDisplay = useAppSelector(selectFormContainer)

  return (
    <div className={styles.formContainer}>
      {currentFormDisplay == FormContainerEnum.IDLE && (
        <>
          <button onClick={() => dispatch(showLogInForm())}>Sign in</button>
          <button onClick={() => dispatch(showSignUpForm())}>Sign Up</button>
        </>
      )}
      {currentFormDisplay == FormContainerEnum.LOG_IN && (
        <>
        <LoginForm
          onSubmit={() => {
            console.log("logiiiin")
          }}
        />
        <button onClick={() => dispatch(showIdle())}>back</button>
        </>
      )}
      {currentFormDisplay == FormContainerEnum.SIGN_UP && (
        <>
        <RegisterForm
          onSubmit={() => {
            console.log("registerrrr")
          }}
        />
                <button onClick={() => dispatch(showIdle())}>back</button>
        </>

      )}
    </div>
  )
}
