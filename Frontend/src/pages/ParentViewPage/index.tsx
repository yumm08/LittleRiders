import DriveHistoryParentView from './DriveHistoryParentView'
import RealTimeParentView from './RealTimeParentView'

/**
 *
 * 모바일 뷰
 *
 */
export default function ParentViewPage() {
  const type = '운행중'

  if (type === '운행중') return <RealTimeParentView />
  return <DriveHistoryParentView />
}
