import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/Redux";
import { getStudentDetails } from "../redux/slices/StudentSlice";
import { User } from "../types";

const ProfileScreen = () => {
  const dispatch: AppDispatch = useDispatch();
  const token = useSelector((state: RootState) => state.StudentAuth.token);
  const user = useSelector((state: RootState) => state.StudentAuth.user) as User;

  useEffect(() => {
    if (token) dispatch(getStudentDetails(token));
  }, [dispatch, token]);

  const {name , email , password , userId} = user;

  return (
    <div>
      <div>user : {name}</div>
      <div>user : {email}</div>
      <div>user : {password}</div>
      <div>user : {userId}</div>
    </div>
  );
};

export default ProfileScreen;
