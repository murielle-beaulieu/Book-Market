import './App.css'
import { Route, Routes } from 'react-router-dom'
import Homepage from "../pages/Homepage";
import UserProfile from "../pages/UserProfile";
import Marketplace from "../pages/Marketplace";

function App() {

  return (
    <>
    <Routes>
      <Route path='/' element={<Homepage />} />
      <Route path='/user' element={<UserProfile/>}/>
      <Route path='/marketplace' element={<Marketplace/>}/>
    </Routes>
    </>
  )
}

export default App
