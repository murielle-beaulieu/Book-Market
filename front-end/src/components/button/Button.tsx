import { type JSX } from "react"
import { changeTheme, selectTheme} from "../../app/slices/themeSlice"
import { useAppDispatch, useAppSelector,} from "../../app/hooks"

export const Button = (): JSX.Element => {
  const dispatch = useAppDispatch()
  const currentTheme = useAppSelector(selectTheme)

  return (
    <div>
        <button
          onClick={() => dispatch(changeTheme())}
        >Change the theme</button>
        <h1>Theme is: {currentTheme}</h1>
    </div>
  )
}
