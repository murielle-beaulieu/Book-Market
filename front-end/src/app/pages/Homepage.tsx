import { Hero } from "../../components/hero/Hero"
import { Navbar } from "../../components/navbar/Navbar"

export const Homepage = () => {
  return (
    <main>
      <Navbar children={undefined}/>
      <Hero children={undefined}/>
      {/* We want a form component for sign-in */}
      {/* We want a form component for sign-up */}
    </main>
  )
}
