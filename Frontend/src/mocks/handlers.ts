import { handlers as operationManagementhandlers } from '@mocks/academy'
import { handlers as childManagementHandlers } from '@mocks/child'

export const handlers = [
  ...childManagementHandlers,
  ...operationManagementhandlers,
]
