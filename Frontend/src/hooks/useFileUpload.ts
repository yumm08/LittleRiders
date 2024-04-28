import React, { useState } from 'react'

export default function useFileUpload() {
  const [uploadImgUrl, setUploadImgUrl] = useState<string>('')
  const onChangeImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const uploadFile = (e.target.files as FileList)[0]
    const reader = new FileReader()
    reader.readAsDataURL(uploadFile)
    reader.onloadend = () => {
      setUploadImgUrl(reader.result as string)
    }
  }
  return { uploadImgUrl, onChangeImageUpload }
}
