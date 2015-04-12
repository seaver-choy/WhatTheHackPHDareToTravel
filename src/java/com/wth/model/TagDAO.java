package com.wth.model;

import java.sql.SQLException;

public interface TagDAO {
        public Tag[] getTags();
	public boolean isTagExisting(Tag tag);
	public void createTag(Tag tag) throws SQLException;
        public void deleteTag(Tag tag);
	public void tagLocation(Location loc, Tag tag) throws SQLException;
	public void untagLocation(Location loc, Tag tag);
        public void tagDare(Dare dare, Tag tag) throws SQLException;
	public void untagDare(Dare dare, Tag tag);
}
