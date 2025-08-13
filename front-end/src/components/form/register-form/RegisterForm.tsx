import { useForm } from "react-hook-form"
import { schema, type RegisterData } from "./register-schema"
import styles from "./registerform.module.scss"
import { zodResolver } from "@hookform/resolvers/zod"

type RegisterFormProps = {
  onSubmit: (data: RegisterData) => unknown
}

export const RegisterForm = ({ onSubmit }: RegisterFormProps) => {
  const {
    handleSubmit,
    register,
    formState: { isSubmitSuccessful, errors },
  } = useForm<RegisterData>({ resolver: zodResolver(schema) })

  return (
    <>
      {isSubmitSuccessful && (
        <div className={styles.success}>Successfully created a new user!</div>
      )}
      <form
        onSubmit={() => handleSubmit(onSubmit)}
        className={styles.register_form}
      >
        <header className={styles.register_header}>
          <h2>Register</h2>
        </header>
        <div className={styles.field}>
          <label>Your first name:</label>
          <input type="text" {...register("firstName")} />
          {errors.firstName && (
            <small style={{ color: "red" }}>{errors.firstName.message}</small>
          )}
        </div>
        <div className={styles.field}>
          <label>Your last name:</label>
          <input type="text" {...register("lastName")} />
          {errors.lastName && (
            <small style={{ color: "red" }}>{errors.lastName.message}</small>
          )}
        </div>
        <div className={styles.field}>
          <label>Your username:</label>
          <input type="text" {...register("displayUsername")} />
          {errors.displayUsername && (
            <small style={{ color: "red" }}>{errors.displayUsername.message}</small>
          )}
        </div>
        <div className={styles.field}>
          <label>Your email:</label>
          <input type="text" {...register("email")} />
          {errors.email && (
            <small style={{ color: "red" }}>{errors.email.message}</small>
          )}
        </div>
        <div className={styles.field}>
          <label>Your password:</label>
          <input type="password" {...register("password")} />
          {errors.password && (
            <small style={{ color: "red" }}>{errors.password.message}</small>
          )}
        </div>
        <div className={styles.submit}>
          <button className="submit">Register</button>
        </div>
      </form>
    </>
  )
}

export default RegisterForm
