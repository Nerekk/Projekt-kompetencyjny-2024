import { useSnackbar } from "vue3-snackbar";
export const useSnack = () => {
  const snackbar = useSnackbar();

  function snackbarSuccess(text) {
    snackbar.add({
      type: 'success',
      text: text
    });
  }
  function snackbarError(text) {
    snackbar.add({
      type: 'error',
      text: text
    });
  }
  return { snackbarSuccess, snackbarError};
}
