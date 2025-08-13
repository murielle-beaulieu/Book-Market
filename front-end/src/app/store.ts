import { configureStore } from "@reduxjs/toolkit";
import themeReducer from "./slices/themeSlice";
import formContainerReducer from "./slices/formContainerSlice";
import authReducer from "./auth/authSlice";
import { apiSlice } from "./api/apiSlice";


export const store = configureStore({
    reducer: {
        // theme
        theme: themeReducer,
        // form container
        formContainer: formContainerReducer,
        // auth
        auth: authReducer,

        // auth api
        [apiSlice.reducerPath]: apiSlice.reducer,
    },
    // async queries
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware().concat(
            apiSlice.middleware,
        ),
})

// export your types
export type RootState = ReturnType<typeof store.getState>;
// whenever we want to access the state using a selector,
// we're going to be able to define the state using this root state type
// and then we'll have access in ts to all of our states

export type AppDispatch = typeof store.dispatch;
// will become useful for the async actions


/* ********  ******  ******** */
/*       EXAMPLE PROVIDED     */
/* ********  ******  ******** */

// React cannot talk directly to Redux, so we need to set up a provider
// then connect our store and app state to react

// import type { Action, ThunkAction } from "@reduxjs/toolkit"
// import { combineSlices, configureStore } from "@reduxjs/toolkit"
// import { setupListeners } from "@reduxjs/toolkit/query"
// import { counterSlice } from "../example-features/counter/counterSlice"
// import { quotesApiSlice } from "../example-features/quotes/quotesApiSlice"
// import { themeSlice } from "../app/slices/themeSlice"
// // import themeReducer from "../app/slices/themeSlice";


// // `combineSlices` automatically combines the reducers using
// // their `reducerPath`s, therefore we no longer need to call `combineReducers`.
// const rootReducer = combineSlices(counterSlice, quotesApiSlice,themeSlice )
// // Infer the `RootState` type from the root reducer
// export type RootState = ReturnType<typeof rootReducer>

// // The store setup is wrapped in `makeStore` to allow reuse
// // when setting up tests that need the same store config

// export const makeStore = (preloadedState?: Partial<RootState>) => {
//   const store = configureStore({
//     reducer: rootReducer,
//     // Adding the api middleware enables caching, invalidation, polling,
//     // and other useful features of `rtk-query`.
//     middleware: getDefaultMiddleware => {
//       return getDefaultMiddleware().concat(quotesApiSlice.middleware)
//     },
//     preloadedState,
//   })
//   // configure listeners using the provided defaults
//   // optional, but required for `refetchOnFocus`/`refetchOnReconnect` behaviors
//   setupListeners(store.dispatch)
//   return store
// }

// export const store = makeStore()

// // Infer the type of `store`
// export type AppStore = typeof store
// // Infer the `AppDispatch` type from the store itself
// export type AppDispatch = AppStore["dispatch"]
// export type AppThunk<ThunkReturnType = void> = ThunkAction<
//   ThunkReturnType,
//   RootState,
//   unknown,
//   Action
// >

/* ********  ******  ******** */
/*       EXAMPLE PROVIDED     */
/* ********  ******  ******** */