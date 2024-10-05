import {createAsyncThunk , createSlice} from '@reduxjs/toolkit'
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
    error : null | object,
    token : object | null
}

const initialState: studentRegisterState =
{
    loading : false,
    error : null,
    token : null,
}

export const registerStudent = createAsyncThunk('/students/registerStudent' , async(student:Student , {rejectWithValue}) =>
{
    try {
        const response = await axiosInstance.post("/student/register" , student)
        return response.data;
    } catch (error) {
        console.log(error)
        return rejectWithValue(error)
    }
})

export const loginStudent = createAsyncThunk('/students/loginStudent' , async(student:Student , {rejectWithValue  }) =>
{
    try {
        const response = await axiosInstance.post("/student/login" , student)
        return response.data;
    } catch (error) {
        console.log(error)
        return rejectWithValue(error);
    }
})

// eslint-disable-next-line react-refresh/only-export-components
const StudentSlice = createSlice(
    {
        name : "students",
        initialState,
        reducers : {},
        extraReducers : (builder) =>
        {
            builder.addCase(registerStudent.pending , (state) =>
            {
                state.loading = true;
                state.error = null;
            }).addCase(registerStudent.fulfilled , (state , {payload}) =>
            {
                state.loading = false;
                state.token = payload;
            }).addCase(registerStudent.rejected , (state , {payload}) =>
            {
                state.loading = false;
                state.error = payload as object ;
            }).addCase(loginStudent.pending , (state) =>
            {
                state.loading = true;
                state.error = null;
            }).addCase(loginStudent.fulfilled , (state , {payload}) =>
            {
                state.loading = false;
                state.token = payload;
            }).addCase(loginStudent.rejected , (state , {payload}) =>
            {
                state.loading = false;
                state.error = payload as object;
            })
        }
    }
)

export default StudentSlice.reducer;