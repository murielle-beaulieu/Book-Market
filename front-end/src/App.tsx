import { BrowserRouter, Route, Routes } from "react-router"
import { Homepage } from "./app/pages/Homepage"
import { ProfilePage } from "./app/pages/ProfilePage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Homepage/>}/>
        <Route path="/profile" element={<ProfilePage/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App;
