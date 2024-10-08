import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Login from "../components/Login";
import Register from "../components/Register";
import ManipalImage from "../assets/images/ManipalImage.jpg";
import { useSelector } from "react-redux";
import { RootState } from "../redux/Redux";

const AuthScreen: React.FC = () => {
  const UserAuthState = useSelector((state: RootState) => state.UserAuth);
  const { token } = UserAuthState;
  
  const [component, setComponent] = useState<string>("Login");
  const navigate = useNavigate();

  useEffect(() => {
    if (token !== null) navigate("/");
  }, [navigate, token]);

  return (
    <div className="w-[100vw] h-[100vh] flex">
      <div className="h-[100%] flex-[.3] bg-primary-color shadow-sm flex flex-col justify-center items-center px-4">
        <div className="w-[100%] p-10 bg-white rounded-md flex items-center justify-center flex-col ">
          <div className="mb-10 flex flex-col justify-center items-center">
            <h1 className="font-bold text-primary-color text-[1.3rem] mb-1">
              Wecome to University Management System
            </h1>
            <h1 className="font-bold text-primary-color text-[.6rem]">
              Wecome to University Management System
            </h1>
          </div>
          {component === "Register" ? (
            <Register setComponent={setComponent} />
          ) : (
            <Login setComponent={setComponent} />
          )}
        </div>
      </div>
      <div className="h-[100%] flex-[.7]">
        <img
          src={ManipalImage}
          alt="Manipal Image"
          className="h-[100%] w-[100%] object-fill"
        />
      </div>
    </div>
  );
};

export default AuthScreen;
