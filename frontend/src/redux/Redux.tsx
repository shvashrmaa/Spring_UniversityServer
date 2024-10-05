import {combineReducers, configureStore} from '@reduxjs/toolkit'
import { thunk } from 'redux-thunk';
import StudentReducer from './slices/StudentSlice'

const rootReducer = combineReducers(
    {
        StudentAuth : StudentReducer
    }
)

const ReduxStore = configureStore({
    reducer : rootReducer,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(thunk)
})

export type AppDispatch = typeof ReduxStore.dispatch;
export type RootState = ReturnType<typeof ReduxStore.getState>

export default ReduxStore;