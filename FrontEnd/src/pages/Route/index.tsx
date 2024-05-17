export default function Route() {
  return (
    <div className="grid h-full w-full grid-cols-12">
      <div className="col-span-5 h-full border-r-2">
        <p className="border-b-2 py-2 text-center text-4xl">노선 목록</p>

        <div className="flex h-[350px] flex-col divide-y-2 overflow-auto">
          {Array.from({ length: 15 }, (_, index) => (
            <div key={index} className="p-2 text-2xl">
              노선 {String.fromCharCode(65 + index)}
            </div>
          ))}
        </div>
      </div>
      <div className="col-span-7 bg-orange-500"></div>
    </div>
  )
}
