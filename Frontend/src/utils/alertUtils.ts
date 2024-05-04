import COLOR_PALETTE from '@style/ColorPalette'
import Swal, { SweetAlertOptions } from 'sweetalert2'

export const showQuestionAlert = (optionList: SweetAlertOptions) => {
  const defaultOptionList: SweetAlertOptions = {
    icon: 'question',
    showDenyButton: true,
    confirmButtonText: '예',
    confirmButtonColor: COLOR_PALETTE.lightgreen,
    denyButtonText: '아니오',
    allowOutsideClick: false,
  }

  return Swal.fire({ ...defaultOptionList, ...optionList })
}

export const showSuccessAlert = (optionList: SweetAlertOptions) => {
  const defaultOptionList: SweetAlertOptions = {
    icon: 'success',
    allowOutsideClick: false,
  }

  return Swal.fire({
    ...defaultOptionList,
    ...optionList,
  })
}

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
