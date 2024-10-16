import {createAsyncThunk , createSlice, PayloadAction} from '@reduxjs/toolkit'
import axiosInstance from '../../utils/axios'


interface User
{
     name? : string,
     email : string,
     password : string,
}

interface UserRegisterState
{
    loading : boolean,
    error : unknown,
    token : string | null,
    user : object | null
}

const initialState: UserRegisterState =
{
    loading : false,
    error : null,
    token : sessionStorage.getItem("token"),
    user : null
}

export const registerUser = createAsyncThunk('/users/registerUser' , async(user:User , {rejectWithValue}) =>
{
    try {
        const response = await axiosInstance.post("/user/register" , user)
        return response.data;
    } catch (error) {
        return rejectWithValue(error)
    }
})

export const loginUser = createAsyncThunk('/users/loginUser' , async(user:User , {rejectWithValue  }) =>
{
    try {
        const response = await axiosInstance.post("/user/login" , user)
        return response.data;
    } catch (error) {
        return rejectWithValue(error);
    }
})

export const getUserDetails = createAsyncThunk('users/getUserDetails' , async(token: string , {rejectWithValue}) =>
{
    try {
        const response = await axiosInstance.get("/protected/user" , {headers : {Authorization : `Bearer ${token}`}})
        return response.data;
    } catch (error) {
        return rejectWithValue(error)
    }
})

// eslint-disable-next-line react-refresh/only-export-components
const UserSlice = createSlice(
    {
        name : "users",
        initialState,
        reducers : {
            setToken(state , action:PayloadAction<string>)
            {
                state.token = action.payload;
                sessionStorage.setItem("token" , action.payload)
            },
            clearToken(state)
            {
                state.token = null;
                sessionStorage.removeItem("token");
            }
        },
        extraReducers : (builder) =>
        {
            builder.addCase(registerUser.pending , (state) =>
            {
                state.loading = true;
                state.error = null;
            }).addCase(registerUser.fulfilled , (state , {payload}) =>
            {
                state.loading = false;
                state.token = payload.Token;
                sessionStorage.setItem("token" , payload.Token);
            }).addCase(registerUser.rejected , (state , action) =>
            {
                state.loading = false;
                state.error = action.error.message ;
            }).addCase(loginUser.pending , (state) =>
            {
                state.loading = true;
                state.error = null;
            }).addCase(loginUser.fulfilled , (state , {payload}) =>
            {
                state.loading = false;
                state.token = payload.Token;
                sessionStorage.setItem("token" , payload.Token)
            }).addCase(loginUser.rejected , (state , action) =>
            {
                state.loading = false;
                state.error = action.error.message;
            }).addCase(getUserDetails.pending , (state) =>
            {
                state.loading = true;
                state.error = null;
            }).addCase(getUserDetails.fulfilled , (state , {payload}) =>
            {
                state.loading = false;
                state.user = payload;
            }).addCase(getUserDetails.rejected ,(state , action) =>
            {
                state.loading = false;
                state.error = action.error.message;
            }
            )
        }
    }
)

export const {setToken , clearToken} = UserSlice.actions;

export default UserSlice.reducer;