package kr.co.littleriders.backend.domain.shuttle;

import kr.co.littleriders.backend.domain.shuttle.entity.ShuttleDrop;

import java.util.List;

public interface ShuttleDropService {

	ShuttleDrop findByAcademyChildId(long academyChildId);

	List<ShuttleDrop> findByShuttleId(long shuttleId);

	List<ShuttleDrop> findByAcademyId(long academyId);

	void deleteAllByShuttleId(long shuttleId);

	void deleteAllByAcademyId(long academyId);

	void delete(ShuttleDrop shuttleDrop);

	void save(ShuttleDrop shuttleDrop);
}
