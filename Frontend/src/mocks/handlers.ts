import { handlers as authHandlers } from '@mocks/auth'
import { handlers as childManagementHandlers } from '@mocks/child'

export const handlers = [...childManagementHandlers, ...authHandlers]
