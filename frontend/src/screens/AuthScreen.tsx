import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Login from "../components/Login";
import Register from "../components/Register";

const AuthScreen: React.FC = () => {
  const [userId, setUserId] = useState<boolean>(false);
  const [component , setComponent] = useState<string>("Login");
  const navigate = useNavigate();
  useEffect(() => {
    if (userId) navigate("/");
  }, []);

  return (
    <div className="w-[100vw] h-[100vh] flex">
      <div className="h-[100%] flex-[.3] border-r-2 bg-orange-500 shadow-sm flex flex-col justify-center items-center px-4">
        <div className="w-[100%] p-10 bg-white rounded-md flex items-center justify-center flex-col ">
          <div className="mb-10 flex flex-col justify-center items-center">
            <h1 className="font-bold text-orange-500 text-[1.3rem] mb-1">
              Wecome to University Management System
            </h1>
            <h1 className="font-bold text-orange-500 text-[.6rem]">
              Wecome to University Management System
            </h1>
          </div>
          {component === "Register" ? <Register setComponent={setComponent}/> : <Login setComponent={setComponent}/>}
        </div>
      </div>
      <div className="h-[100%] flex-[.7]">right Side of Authentication Paage</div>
    </div>
  );
};

export default AuthScreen;
