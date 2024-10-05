import React, { useEffect } from 'react'
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { RootState } from '../redux/Redux';

const HomeScreen:React.FC = () => {
    const StudentAuthState = useSelector((state:RootState) => state.StudentAuth);
    const {token} = StudentAuthState;
    const navigate = useNavigate();

    useEffect(() => {
        if(token === null) navigate("/authentication")
    }, [navigate, token])

    useEffect(() =>
    {
        console.log(token)
    },[token])
    
  return (
    <div>This is HomeScreen</div>
  )
}

export default HomeScreen;