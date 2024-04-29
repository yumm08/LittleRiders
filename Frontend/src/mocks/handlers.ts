
import { handlers as authHandlers } from '@mocks/auth'
import { handlers as childManagementHandlers } from '@mocks/child'
import { handlers as operationManagementhandlers } from '@mocks/academy'

export const handlers = [...childManagementHandlers, ...authHandlers,...operationManagementhandlers,]

