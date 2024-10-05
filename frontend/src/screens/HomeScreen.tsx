import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { RootState } from '../redux/Redux';

const HomeScreen:React.FC = () => {
    const [token , setToken] = useState<null | object>(useSelector((state:RootState) => state.StudentAuth.token))
    const navigate = useNavigate();

    useEffect(() => {
        if(token === null) navigate("/authentication")
    }, [])

    useEffect(() =>
    {
        console.log(token)
    },[token])
    
  return (
    <div>This is HomeScreen</div>
  )
}

export default HomeScreen;