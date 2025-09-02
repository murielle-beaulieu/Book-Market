/* eslint-disable @typescript-eslint/no-unsafe-member-access */
/* eslint-disable no-restricted-imports */
/* eslint-disable @typescript-eslint/no-unsafe-assignment */
import { useDispatch } from "react-redux"
import {
  useLoginMutation,
  useRegisterMutation,
} from "../../../app/auth/authApiSlice"
import { setCredentials } from "../../../app/auth/authSlice"
import { useAppSelector } from "../../../app/hooks"
import {
  FormContainerEnum,
  selectFormContainer,
  showIdle,
  showLogInForm,
  showSignUpForm,
} from "../../../app/slices/formContainerSlice"
import type { LoginData } from "../../form/login-form/login-schema"
import LoginForm from "../../form/login-form/LogInForm"
import RegisterForm from "../../form/register-form/RegisterForm"
import styles from "./formContainer.module.scss"
import { useNavigate } from "react-router"
import type { RegisterData } from "../../form/register-form/register-schema"

export const FormContainer = () => {
  // const dispatch = useAppDispatch()
  const dispatch = useDispatch()

  const currentFormDisplay = useAppSelector(selectFormContainer)

  const [loginMutation] = useLoginMutation()
  const [registerMutation] = useRegisterMutation()

  const navigate = useNavigate()

  let token = null

  async function handleLogin(data: LoginData) {
    try {
      const userData = await loginMutation({
        email: data.email,
        password: data.password,
      }).unwrap()
      token = userData.token
      dispatch(setCredentials({ token }))
      await navigate("/profile")
    } catch (e) {
      console.log(e)
    }
  }

  async function handleRegister(data: RegisterData) {
    try {
      const registerData = await registerMutation({
        firstName: data.firstName,
        lastName: data.lastName,
        displayUsername: data.displayUsername,
        email: data.email,
        password: data.password,
      }).unwrap()
      token = registerData.token

      dispatch(setCredentials({ token }))
    } catch (e) {
      console.log(e)
    }
  }

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
          <LoginForm onSubmit={handleLogin} />
          <button onClick={() => dispatch(showIdle())}>back</button>
        </>
      )}
      {currentFormDisplay == FormContainerEnum.SIGN_UP && (
        <>
          <RegisterForm onSubmit={handleRegister} />
          <button onClick={() => dispatch(showIdle())}>back</button>
        </>
      )}
    </div>
  )
}
