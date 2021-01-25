package com.example.MiniReddit.domain.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.MiniReddit.domain.discovery.Discovery;
import com.example.MiniReddit.domain.discovery.DiscoveryDao;
import com.example.MiniReddit.domain.user.UserDao;

public class DiscoveryService {
	private final DiscoveryDao discoveryDao = new DiscoveryDao();
	private final DiscoveryMapper discoveryMapper = new DiscoveryMapper();

	public void add(DiscoverySaveRequest saveRequest) {
		Discovery discoveryToSave = discoveryMapper.map(saveRequest);
		discoveryDao.save(discoveryToSave);
	}

	public List<DiscoveryBasicInfo> findAll() {
		return discoveryDao.findAll()
				.stream().map(discoveryMapper::map)
				.collect(Collectors.toList());
	}

	public List<DiscoveryBasicInfo> findAllByCategory(int categoryId) {
		return discoveryDao.findByCategory(categoryId)
				.stream().map(discoveryMapper::map)
				.collect(Collectors.toList());
	}

	private static class DiscoveryMapper {
		private final UserDao userDao = new UserDao();

		DiscoveryBasicInfo map(Discovery d) {
			return new DiscoveryBasicInfo(
					d.getId(),
					d.getTitle(),
					d.getUrl(),
					d.getDescription(),
					d.getDateAdded()
			);
		}

		Discovery map(DiscoverySaveRequest ds) {
			return new Discovery(
					ds.getTitle(),
					ds.getUrl(),
					ds.getDescription(),
					LocalDateTime.now(),
					ds.getCategoryId(),
					userDao.findByUsername(ds.getAuthor())
							.orElseThrow()
							.getId()
			);
		}
	}
}
