import { createSlice } from '@reduxjs/toolkit';

export enum Theme {
    DARK = "Dark",
    LIGHT = "Light"
}

type ThemeState = {
  value: Theme;
}

const initialState: ThemeState = {
 value: Theme.LIGHT,
};

export const themeSlice = createSlice({
    name: 'theme',
    initialState,
    reducers: {
        changeTheme: (state) => {
          state.value = (state.value == Theme.DARK ? Theme.LIGHT : Theme.DARK);
        },
    }, selectors: {
    selectTheme: theme => theme.value,
  }
})

export const { changeTheme } = themeSlice.actions;
export const { selectTheme } = themeSlice.selectors
export default themeSlice.reducer;

