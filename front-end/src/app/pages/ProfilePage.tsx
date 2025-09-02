/* eslint-disable @typescript-eslint/no-unsafe-member-access */
/* eslint-disable @typescript-eslint/no-unsafe-assignment */

// eslint-disable-next-line no-restricted-imports
import { useDispatch } from "react-redux"
import { useGetCurrentUserQuery } from "../auth/authApiSlice"
import { setUser } from "../auth/authSlice"
// import { setUser } from "../auth/authSlice";

export const ProfilePage = () => {
  const { data: currentUser, isLoading, isError } = useGetCurrentUserQuery({})
  const dispatch = useDispatch()

  if (isError) {
    return (
      <div>
        <h1>There was an error!!!</h1>
      </div>
    )
  }

  if (isLoading) {
    return (
      <div>
        <h1>Loading...</h1>
      </div>
    )
  }

  // eslint-disable-next-line @typescript-eslint/no-unsafe-argument
  if (currentUser) dispatch(setUser(currentUser));
  
  return (
    <>
      {/* <Navbar children={undefined}/> */}
      <h1>Hello {currentUser.firstName}</h1>
      <h1>Logged in</h1>
    </>
  )
}
