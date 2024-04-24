
type Props = {
    historyId : number
}

export default function DriveHistoryNaverMap({historyId}: Props) {
    //TODO history ID를 통해 자세한 정보 데이터 호출 후 지도에 뿌려주기
    // 이렇게 하면 데이터 호출할 때 마다 지도 깜빡이려나..?
    // const {data,isLoading} = fetchDetailDriveHistory(historyId)
  return (
    <div>{historyId}</div>
  )
}