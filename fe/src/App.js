import { Routes, Route } from "react-router-dom";
import './App.css';
import { ROOT_PATH } from "./Const";
import Login from './app/views/Login/LoginForm';

function App() {
  return (
    <>
    <h1>Home</h1>
      {/* <Login /> */}
      {/* <Routes>
        <Route path={ROOT_PATH + "/login"} element={<Login />} />
      </Routes> */}
    </>
  );
}

export default App;
