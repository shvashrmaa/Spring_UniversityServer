import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/Redux";
import { getUserDetails } from "../redux/slices/UserSlice";
import { User } from "../types";

const ProfileScreen = () => {
  const dispatch: AppDispatch = useDispatch();
  const token = useSelector((state: RootState) => state.UserAuth.token);
  const user = useSelector((state: RootState) => state.UserAuth.user) as User;

  useEffect(() => { 
    if (token) dispatch(getUserDetails(token));
  }, [dispatch, token]);

  return (
    <div>
      <div>user : {user?.name}</div>
      <div>user : {user?.email}</div>
      <div>user : {user?.password}</div>
      <div>user : {user?.userId}</div>
    </div>
  );
};

export default ProfileScreen;
