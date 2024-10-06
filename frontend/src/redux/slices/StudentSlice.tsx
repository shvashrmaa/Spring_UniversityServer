import {createAsyncThunk , createSlice, PayloadAction} from '@reduxjs/toolkit'
import axiosInstance from '../../utils/axios'


interface Student
{
     name? : string,
     email : string,
     password : string,
}

interface studentRegisterState
{
    loading : boolean,
    error : unknown,
    token : string | null,
    user : object | null
}

const initialState: studentRegisterState =
{
    loading : false,
    error : null,
    token : sessionStorage.getItem("token"),
    user : null
}

export const registerStudent = createAsyncThunk('/students/registerStudent' , async(student:Student , {rejectWithValue}) =>
{
    try {
        const response = await axiosInstance.post("/student/register" , student)
        return response.data;
    } catch (error) {
        return rejectWithValue(error)
    }
})

export const loginStudent = createAsyncThunk('/students/loginStudent' , async(student:Student , {rejectWithValue  }) =>
{
    try {
        const response = await axiosInstance.post("/student/login" , student)
        return response.data;
    } catch (error) {
        return rejectWithValue(error);
    }
})

export const getStudentDetails = createAsyncThunk('students/getStudentDetails' , async(token: string , {rejectWithValue}) =>
{
    try {
        const response = await axiosInstance.get("/protected/student" , {headers : {Authorization : `Bearer ${token}`}})
        return response.data;
    } catch (error) {
        return rejectWithValue(error)
    }
})

// eslint-disable-next-line react-refresh/only-export-components
const StudentSlice = createSlice(
    {
        name : "students",
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
            builder.addCase(registerStudent.pending , (state) =>
            {
                state.loading = true;
                state.error = null;
            }).addCase(registerStudent.fulfilled , (state , {payload}) =>
            {
                state.loading = false;
                state.token = payload.token;
                sessionStorage.setItem("token" , payload.token);
            }).addCase(registerStudent.rejected , (state , action) =>
            {
                state.loading = false;
                state.error = action.error.message ;
            }).addCase(loginStudent.pending , (state) =>
            {
                state.loading = true;
                state.error = null;
            }).addCase(loginStudent.fulfilled , (state , {payload}) =>
            {
                state.loading = false;
                state.token = payload.token;
                sessionStorage.setItem("token" , payload.token)
            }).addCase(loginStudent.rejected , (state , action) =>
            {
                state.loading = false;
                state.error = action.error.message;
            }).addCase(getStudentDetails.pending , (state) =>
            {
                state.loading = true;
                state.error = null;
            }).addCase(getStudentDetails.fulfilled , (state , {payload}) =>
            {
                state.loading = false;
                state.user = payload;
            }).addCase(getStudentDetails.rejected ,(state , action) =>
            {
                state.loading = false;
                state.error = action.error.message;
            }
            )
        }
    }
)

export const {setToken , clearToken} = StudentSlice.actions;

export default StudentSlice.reducer;