import Swal, { SweetAlertOptions } from 'sweetalert2'

export const showErrorAlert = (optionList: SweetAlertOptions) => {
  const defaultOptionList: SweetAlertOptions = {
    icon: 'error',
    allowOutsideClick: false,
  }
  return Swal.fire({
    ...defaultOptionList,
    ...optionList,
  })
}
