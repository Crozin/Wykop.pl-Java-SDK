package com.crozin.wykop.sdk.repository;

import java.util.List;

import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Session;
import com.crozin.wykop.sdk.domain.Profile;

public class ProfilesRepository extends AbstractRepository {
	public ProfilesRepository(Session session) {
		super(session);
	}

	public List<Profile> getByRank() {
		return session.getResultList(new Command("rank", "index"), Profile.class);
	}
}
