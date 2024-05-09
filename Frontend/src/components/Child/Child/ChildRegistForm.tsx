import ChildRegistFormBody from '@components/Child/Child/ChildRegistFormBody'
import ChildRegistFormFooter from '@components/Child/Child/ChildRegistFormFooter'

import { ChildRegistInfo } from '@types'
import { FormProvider, useForm } from 'react-hook-form'

export default function ChildRegistForm() {
  const methodList = useForm<ChildRegistInfo>({ mode: 'onBlur' })

  const onSubmit = (childRegistInfo: ChildRegistInfo) => {
    console.log(childRegistInfo)
  }

  return (
    <FormProvider {...methodList}>
      <form
        className="flex flex-col gap-4"
        onSubmit={methodList.handleSubmit(onSubmit)}
      >
        <ChildRegistFormBody />

        <ChildRegistFormFooter />
      </form>
    </FormProvider>
  )
}
